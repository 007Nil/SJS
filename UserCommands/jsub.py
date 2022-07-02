# Script for submitting jobs
from ntpath import join
import argparse


def main():
    parser = argparse.ArgumentParser(description='Program to submit jobs to Simple Job Scheduler')
    parser.add_argument("--queue","-q",help="name you the job where jobs will be submited")
    parser.add_argument("--resources","-l",help="Add SJS resources (ncpus,mem, etc...)")
    namespace, extra = parser.parse_known_args()

    job_command = " ".join(extra)
    print(namespace.queue)
    # print(" ".join(args.binary))

if __name__ == "__main__":
    main()