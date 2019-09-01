package michalwds.repositories;

import michalwds.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    // zamiast value, które byłoby natywne, dodajemy JPQL//   ?1 = argumenty licząc od lewej strony, np 1
    //@Query ("select * from planets where planet_name = ?1") //// SQL


    @Query("select p from Planet p where p.planetName=?1")
    Optional<Planet> findPlanetByPlanetName(String planetName);

    @Transactional  //running persistance hibernate, can delete and do anything to delete by myself not automatical
    @Modifying  //without this can't modifing planets (for example deleting)
    @Query("delete from Planet p where p.planetName=?1")
    void deletePlanetByPlanetName(String planetName);


    @Query("select p from Planet p where distanceFromSun<=?1")
    List<Planet> findPlanetsByDistanceFromSun(Long distance);


}
