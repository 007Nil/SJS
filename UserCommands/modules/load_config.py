# config files should to be in /etc/sjs.cfg file for prod
# for local testing purpose will store in local conf dir
SERVER_PORT=10015
CONFIG_FILE="etc/sjs.cfg" # for prod use /etc/sjs.cfg

def read_config(config_key):
    with open(CONFIG_FILE,'r') as cfg_file:
        file_data = cfg_file.readlines()
    
    for each_line in file_data:
        if (each_line.startswith(config_key)):
            return each_line.replace("\n","").split("=")[1]

# read_config("SJS_SERVER")



