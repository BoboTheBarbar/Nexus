read -p "please shut all nc-sub-containers down first at https://192.168.178.44:8080 or https://192.168.178.44:8080/containers. Press enter to continue or ctrl+c to abort." CONFIRMATION
sudo docker stop nextcloud-aio-mastercontainer
sudo docker ps --filter "status=exited"
sudo docker container prune
sudo docker network rm nextcloud-aio
sudo docker volume ls --filter "dangling=true"
sudo docker volume prune