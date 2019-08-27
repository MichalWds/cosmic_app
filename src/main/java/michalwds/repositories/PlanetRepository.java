package michalwds.repositories;

import michalwds.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    // zamiast value, które byłoby natywne, dodajemy JPQL//   ?1 = argumenty licząc od lewej strony, np 1
    // Query ("select * from planets where planet_name = ?1) SQL
    @Query("select p from Planet p where p.planetName = ?1") //JPQL
    Optional<Planet> findPlanetByPlanetName(String planetName);
}
