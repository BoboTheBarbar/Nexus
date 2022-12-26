# Namespaces
Consider two families Smith and Williams. Both have someone called Mark.
- Within the family, both would be addressed by mark. But outside they would be called by their full names to distinguish them.
- The houses would be namespaces within k8s.

As long as we are small, we don't have to worry about namespaces.

## namespace default
We were inside one of those houses all the time, the default namespace. It is created automatically.

## namespace kubesystem
K8s creates other services and pods for internal purpose like for  networking dns etc.

## namespace kube-public
resources available to all users

# Namespaces
We can have a namespace for Dev and Prod with its own set of policies.
Within a namespace, the resources can refer to each other by simply using their name. Like `mysql.connect("db-service")`.
When we refer to another service outside, we need to address it by the name that the local dns service did register it under when the service was created. Which would include its namespace within Dev:

```mysql.connect("db-service.dev.svc.cluster.local")```

| Service Name | Namespace | Service | domain
| -- | -- | -- | --
| db-service | dev | cluster | local

## Commands
```
get pods --namespace=kube-system
get pods --all-namespaces
get create -f pod-def.yaml --namespace=dev
// this can also be done within the definition 
// file under metadata

// changing underlying namespace permanently
kubectl config set-context $(kubectl config current-context) -- namespace=dev
```
