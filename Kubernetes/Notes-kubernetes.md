
# K8s cheatsheet
# Commands
- kubectl get replicationcontroller // or replicaset
- kubectl get all -o wide
- kubectl scale --replicas=6 replicaset set-name // will not update the definition file
- kubectl [command] [TYPE] [NAME] -o <output_format>
  - `-o wide`: Output in the plain-text format with any additional information.
  - kubectl get pod web-app -o yaml
- kubectl edit pod [NAME] // often forbidden -> creates a new file that can be used to replace a pod.
  - kubectl replace --force -f [FILE]
- kubectl describe configmaps
  - kubectl describe node node01 | grep Taints
- Imperative eine Dekleration erzeugen: 
- [IMPERATIVSTATEMENT] --dry-run=client -o yaml > [DATEINAME]


## Kubernetes Architecture
- **Node** (formally Minion)
  - Worker machine where kybernetes will run
  - Containers will be launched here
- **Cluster** is a set of nodes
  - sharing loads
  - fail safety
- **Master**
  - Node that watches over other worker nodes. 
  - Orchestration
- **Components** - By installing kubernetes, we actually install following six components:
  - **API** Server: Frontend 4 k8s
  - **etcd**: Key-calue store to manage the cluster. Everything to manage the cluster
  - **kubelet**: Agent running on each Node on the cluster. Responsible for making sure that the containers are running on the nodes as expected.
  - **Container Runtime**: Underlying software to run containers. e.g. docker
  - **Controller (Brain)**: noticing and responding when something goes down and decide to bring new ones up.
  - **Scheduler**: distributing work

# Docker security