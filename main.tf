provider "docker" {
  version = "~> 2.7"
  host    = "ssh://ec2-user@${aws_instance.outrun_instance.public_dns}:22"
}

#network
resource "docker_network" "outrun" {
  name = "outrun"
}

# remote images
data "docker_registry_image" "outrun" {
  name = "725705545716.dkr.ecr.eu-west-2.amazonaws.com/outrun:latest"
}

data "docker_registry_image" "outrun_web" {
  name = "725705545716.dkr.ecr.eu-west-2.amazonaws.com/outrun-web:latest"
}

# local images
resource "docker_image" "outrun" {
  name = data.docker_registry_image.outrun.name
  pull_triggers = [
    data.docker_registry_image.outrun.sha256_digest
  ]
}

resource "docker_image" "outrun_web" {
  name = data.docker_registry_image.outrun_web.name
  pull_triggers = [
    data.docker_registry_image.outrun_web.sha256_digest
  ]
}

#containers
resource "docker_container" "outrun" {
  image = docker_image.outrun.latest
  name  = "outrun"
  env = [
    "MYSQL_HOST=${var.mysql_host}",
    "MYSQL_PORT=${var.mysql_port}",
    "MYSQL_DATABASE=${var.mysql_database}",
    "MYSQL_USERNAME=${var.mysql_username}",
    "MYSQL_PASSWORD=${var.mysql_password}"
  ]
  ports {
    internal = 81
    external = 8081
  }
}

resource "docker_container" "outrun_web" {
  image = docker_image.outrun_web.latest
  name  = "outrun-web"
  ports {
    internal = 80
    external = 8080
  }
}

// shared/providers.tf
provider "aws" {
  region     = "${var.aws_region}"
  access_key = "${var.aws_access_key_id}"
  secret_key = "${var.aws_secret_access_key}"
}

resource "aws_instance" "outrun_instance" {
  ami           = "ami-0a669382ea0feb73a"
  instance_type = "t2.micro"
  key_name = "vmaciver_key_pair_euwest2"
}

# Our default security group to access
# the instances over SSH and HTTP
resource "aws_security_group" "default" {
    name = "terraform_example"
    description = "Used in the terraform"

    # SSH access from anywhere
    ingress {
        from_port = 22
        to_port = 22
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }

    # HTTP access from anywhere
    ingress {
        from_port = 443
        to_port = 443
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }

    # HTTP access from anywhere
    ingress {
        from_port = 80
        to_port = 80
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }

    # outbound internet access
    egress {
        from_port = 0
        to_port = 0
        protocol = "-1"
        cidr_blocks = ["0.0.0.0/0"]
    }
}


resource "aws_elb" "web" {
  name = "terraform-example-elb"

  # The same availability zone as our instance
  availability_zones = ["${aws_instance.web.availability_zone}"]

  listener {
    instance_port = 80
    instance_protocol = "http"
    lb_port = 80
    lb_protocol = "http"
  }

  # The instance is registered automatically
  instances = ["${aws_instance.web.id}"]
}

resource "aws_instance" "web" {
  # The connection block tells our provisioner how to
  # communicate with the resource (instance)
  connection {
    # The default username for our AMI
    user = "ubuntu"

    # The path to your keyfile
    key_file = "${var.key_path}"
  }

  instance_type = "t2.micro"

  # Lookup the correct AMI based on the region
  # we specified
  ami = "${lookup(var.aws_amis, var.aws_region)}"

  # The name of our SSH keypair you've created and downloaded
  # from the AWS console.
  #
  # https://console.aws.amazon.com/ec2/v2/home?region=us-west-2#KeyPairs:
  #
  key_name = "${var.key_name}"

  # Our Security group to allow HTTP and SSH access
  security_groups = ["${aws_security_group.default.name}"]

  # We run a remote provisioner on the instance after creating it.
  provisioner "file" {
      source = "./wso2-esb/"
      destination = "/tmp/"
  } 
provisioner "remote-exec" {
   inline = [
        "sh /tmp/install-docker.sh"
    ]
  }
}