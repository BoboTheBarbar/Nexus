#!/bin/bash
# run with: sh sript_name.sh
# https://help.ubuntu.com/community/Beginners/BashScripting

# Variables
YES=y
DATA_DIR=/hive1/nextcloud/data/
HIVE_DIR=/hive1/
PLEX_DIR=/home/boris/plex

# --- Shutdown Containers ---
read -p "shut down nextcloud and plex y/n? " SHUTDOWN
read -p "please shut all nc-sub-containers down first at https://192.168.178.44:8080. Press enter to continue." CONFIRMATION

if [ $SHUTDOWN = $YES ]; then
        cd $PLEX_DIR
        docker-compose down
        docker stop nextcloud-aio-mastercontainer
fi

# --- Check if mount directory has to be cleaned ---
if [ "$(ls -A $HIVE_DIR)" ]; then
        echo "$HIVE_DIR is not empty."
        ls $HIVE_DIR
        read -p "clean $HIVE_DIR* y/n? " DELETE
        if [ "$(ls -A $DATA_DIR)" ]; then
                echo "CAUTION! $DATA_DIR is not empty. Make sure everything there should be deleted."
        else
                if [ $DELETE = $YES ]; then
                        echo "$DATA_DIR verified to be empty, cleaning $HIVE_DIR..."
                        rm -r $HIVE_DIR*
                        if [ "$(ls -A $HIVE_DIR)" ]; then
                                echo "CAUTION! $HIVE_DIR is still not empty."
                        else
                                echo "$HIVE_DIR verified to be empty."
                        fi
                fi
        fi
else
        echo "$HIVE_DIR is already empty, nothing to clean."
fi

# --- Mount drive to dir ---
read -p "Mount $HIVE_DIR y/n? " MOUNT

if [ $MOUNT = $YES ]; then
#  mount /dev/sdb1 /hive1/
        echo "mounting..."
        mount /dev/sdb1 $HIVE_DIR
        if [ "$(ls -A $DATA_DIR)" ]; then
                echo "mounted"
                echo "$DATA_DIR does contain data now."
        else
                echo "CAUTION! $DATA_DIR is still empty."
        fi
fi

read -p "start nextcloud and plex y/n? " START
if [ $START = $YES ]; then
        cd $PLEX_DIR
        docker-compose up -d
        docker start nextcloud-aio-mastercontainer
        read -p "In case the mastercontainer did start up: Please start all nc-sub-containers at https://192.168.178.44:8080. Press enter to continue." CONFIRMATION
fi
