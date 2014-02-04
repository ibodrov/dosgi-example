# Building

Clone and run

```no-highlight
mvn install
```

# Installation

*(tested on fabric8-karaf-1.0.0.redhat-337)*

## Standalone variant

```no-highlight
install -s mvn:com.acme/producer-model/1.0-SNAPSHOT
install -s mvn:com.acme/producer-api/1.0-SNAPSHOT
install -s mvn:com.acme/producer-impl/1.0-SNAPSHOT
install -s mvn:com.acme/consumer/1.0-SNAPSHOT
```

## DOSGi variant

```no-highlight
fabric:create --clean --wait-for-provisioning

profile-create --parents feature-dosgi acme-producer
profile-edit --bundles mvn:com.acme/producer-model/1.0-SNAPSHOT acme-producer
profile-edit --bundles mvn:com.acme/producer-api/1.0-SNAPSHOT acme-producer
profile-edit --bundles mvn:com.acme/producer-impl/1.0-SNAPSHOT acme-producer

profile-create --parents feature-dosgi acme-consumer
profile-edit --bundles mvn:com.acme/producer-model/1.0-SNAPSHOT acme-consumer
profile-edit --bundles mvn:com.acme/producer-api/1.0-SNAPSHOT acme-consumer
profile-edit --bundles mvn:com.acme/consumer/1.0-SNAPSHOT acme-consumer
```

# Running

Consumer will poll and invoke service every 3s. Check `data/log/karaf.log` (for standalone variant) or `instances/consumer/data/log/karaf.log` (for distributed).

Distributed version should fail with `ClassNotFound` exception.