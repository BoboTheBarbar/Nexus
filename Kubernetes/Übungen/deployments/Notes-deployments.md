# Deployments
Higher level than replicasets - always also deploy replicasets, since they are the controller (brain) within the k8s architecture.
- rolling updates
- rollback changes
- pause environment
  - update multiple configuration changes at different points without them beeing applied
  - than apply them together
- identical to replicaset definition