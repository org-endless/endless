## Build Shell

- Set Version

```shell
mvn versions:set -DprocessAllModules=true -DprocessFromLocalAggregationRoot=true -DnewVersion="0.0.6-SNAPSHOT"
```

- Version Commit

```shell
mvn version:commit
```
