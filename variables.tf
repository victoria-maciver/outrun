variable "mysql_host" {
  type = string
}

variable "mysql_port" {
  type = string
}

variable "mysql_database" {
  type = string
}

variable "mysql_username" {
  type = string
}

variable "mysql_password" {
  type = string
}

variable "aws_region" {
  type = string
}

variable "aws_access_key_id" {
  type = string
}

variable "aws_secret_access_key" {
  type = string
}

variable "aws_public_dns" {
  type = string
}

variable "key_path" {
    type = string
}

variable "key_name" {
    type = string
}


variable "aws_amis" {
    default = {
        eu-west-2 = "ami-0a669382ea0feb73a"
    }
}