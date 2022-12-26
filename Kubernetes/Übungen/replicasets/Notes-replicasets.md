## Controller
Controllers are the brain behind k8s. They are teh processes that monitor k8s processes and act accordingly.

### Replication Controller / Set
- Ensures that the specified number of pods are running at all times
- Loadbalancing: Scales application accross different nodes.
- Replication **Controller** is not the same as Replicatino **Set**. Controller is old and Set is new. 
- Repl. **Set** does also need to specify a **selctor** under spec and is one of the major differences.
  - It enables the set to also include other pods wich are not under template.
  - If we skip the selector definition, it assues it to be the same as in the pod definition file.

### Labels and Selectors
Why do we label?
- Replicaset monitors existing pods when they were already created. 