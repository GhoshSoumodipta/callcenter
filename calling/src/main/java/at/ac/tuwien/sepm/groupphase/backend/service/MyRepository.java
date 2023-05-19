package at.ac.tuwien.sepm.groupphase.backend.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface MyRepository {
    <T> Mono<T> findOne(Query query, Class<T> entityClass);
    <T> Mono<T> findOne(Query query, Class<T> entityClass, String name);
    <T> Flux<T> find(Query query, Class<T> entityClass);
    <T> Flux<T> find(Query query, Class<T> entityClass, String collectionName);
    <T> Flux<T> findAllAndRemove(Query query, Class<T> entityClass);
    <T> Flux<T> insertAll(Collection<? extends T> batchToSave);
    Mono<Boolean> collectionExists(String collectionName);
    Mono<MongoCollection<Document>> createCollection(String collectionName);
    <T> Mono<T> save(T objectToSave);
    Mono<DeleteResult> remove(Object object);
}
