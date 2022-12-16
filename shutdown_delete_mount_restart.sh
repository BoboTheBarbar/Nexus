#!/bin/bash
# run with: sh sript_name.sh
# https://help.ubuntu.com/community/Beginners/BashScripting

# Variables
YES=y
DATA_DIR=/hive1/nextcloud/data/
HIVE_DIR=/hive1/

# --- Shutdown Containers ---
read -p "shut down nextcloud and plex y/n? " SHUTDOWN

if [ $SHUTDOWN = $YES ]; then 
	cd nextcloud
	docker-compose down
	cd ../plex
	docker-compose down
	cd ..
fi

# --- Check if mount directory has to be cleaned ---
if [ $(ls -A $HIVE_DIR) ]; then 
	echo "$HIVE_DIR is not empty."
	ls $HIVE_DIR
	read -p "clean $HIVE_DIR* y/n? " DELETE
	if [ $(ls -A $DATA_DIR) ]; then 
		echo "CAUTION! $DATA_DIR is not empty. Make sure everything there should be deleted."
	else
		if [ $DELETE = $YES ]; then
			echo "$DATA_DIR verified to be empty, cleaning $HIVE_DIR..."
			rm -r $HIVE_DIR*
			if [ $(ls -A $HIVE_DIR) ]; then 
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
	cd nextcloud
	docker-compose up -d
	cd ../plex
	docker-compose up -d
	cd ..
fi
