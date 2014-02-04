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

container-create-child --profile acme-producer root producer
container-create-child --profile acme-consumer root consumer
```

# Running

Consumer will poll and invoke service every 3s. Check `data/log/karaf.log` (for standalone variant) or `instances/consumer/data/log/karaf.log` (for distributed).

Distributed version will fail with `ClassNotFound` exception. E.g:
```no-highlight
Caused by: java.lang.ClassNotFoundException: com.acme.producer.model.NestedEntity not found by com.acme.consumer [83]
	at org.apache.felix.framework.BundleWiringImpl.findClassOrResourceByDelegation(BundleWiringImpl.java:1532)
	at org.apache.felix.framework.BundleWiringImpl.access$400(BundleWiringImpl.java:75)
	at org.apache.felix.framework.BundleWiringImpl$BundleClassLoader.loadClass(BundleWiringImpl.java:1955)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)[:1.7.0_25]
	at org.apache.felix.framework.Felix.loadBundleClass(Felix.java:1870)
	at org.apache.felix.framework.BundleImpl.loadClass(BundleImpl.java:937)
	at io.fabric8.dosgi.util.internal.BundleToClassLoaderAdapter$3.run(BundleToClassLoaderAdapter.java:101)
	at io.fabric8.dosgi.util.internal.BundleToClassLoaderAdapter$3.run(BundleToClassLoaderAdapter.java:99)
	at java.security.AccessController.doPrivileged(Native Method)[:1.7.0_25]
	at io.fabric8.dosgi.util.internal.BundleToClassLoaderAdapter.loadClass(BundleToClassLoaderAdapter.java:99)
	at java.lang.Class.forName0(Native Method)[:1.7.0_25]
	at java.lang.Class.forName(Class.java:270)[:1.7.0_25]
	at io.fabric8.dosgi.util.ClassLoaderObjectInputStream.load(ClassLoaderObjectInputStream.java:65)[86:io.fabric8.fabric-dosgi:1.0.0.redhat-337]
        ...
```
