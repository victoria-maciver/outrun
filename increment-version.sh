version=$(cat VERSION)
semantics=( ${version//./ })
major="${semantics[0]}"
minor="${emantics[1]}"
patch="$(( ${semantics[2]} + 1 ))"
echo "${major}.${minor}.${patch}" > ./VERSION