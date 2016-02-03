package com.example.nosql.repository;

import java.util.List;

import com.example.nosql.domain.Manufacturer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, ObjectId> {

	Manufacturer findById(ObjectId id);

	List<Manufacturer> findByName(String name);

	List<Manufacturer> findAll();

	@Query(value = "{ 'name' : ?0, 'yoc' : ?1}")
	List<Manufacturer> findByNameAndYoc(String name, int yoc);
}