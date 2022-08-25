# Script for submitting jobs
# Syntax:
# jsub -q <queue-name> -l ncpus=1:mem=1000mb -- /bin/sleep 1000
import argparse
from http import client
from pydoc import cli
import sys
import os
import socket
from time import sleep

# Custom Imports
import modules.load_config as lc
import modules.global_var as gv

emply_namespace = "Namespace(queue=None, resources=None)"
job_submit_cmd = ""

# Socket Info
SERVER=lc.read_config("SJS_SERVER")
PORT= gv.PORT
ADDRESS=(SERVER,PORT)

class SubmitCommand:
    def __init__(self,command_name=None,queue=None,resource=None,job_name=None,user=None):
        self.command_name = "jsub"
        self.queue = queue
        self.resource = resource
        self.job_name = job_name
        self.user = user

    def __str__(self):
        return f"command_name:{self.command_name}, queue:{self.queue}, resource:{self.resource}, job_name:{self.job_name},user:{self.user}"

def send_request(submit_arg):
    client = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    client.connect(ADDRESS)
    message = f"{submit_arg.__str__()}\n"
    client.send(message.encode())
    data = client.recv(1024).decode()
    print(data.replace("\n",""))

    client.close()


def main():

    submit_object = SubmitCommand()
    parser = argparse.ArgumentParser(description='Program to submit jobs to Simple Job Scheduler')
    parser.add_argument("--queue","-q",help="name you the job where jobs will be submited")
    parser.add_argument("--resources","-l",help="Add SJS resources (ncpus,mem)")
    namespace, extra = parser.parse_known_args()

    job_command = " ".join(extra)

    if len(job_command) == 0:
        print("Job script will be read from standard input. Submit with CTRL+D.")
        std_input = sys.stdin.read()
        print(std_input)

    if str(namespace) != emply_namespace:
        if namespace.queue != None:
            submit_object.queue = namespace.queue
        if namespace.resources != None:
            submit_object.resource = namespace.resources

    if len(job_command) > 0:
        submit_object.job_name = job_command

    if str(namespace) == emply_namespace and len(job_command) == 0:
        parser.print_help()

    submit_object.user  = os.getlogin()
    send_request(submit_object)


if __name__ == "__main__":
    main()
