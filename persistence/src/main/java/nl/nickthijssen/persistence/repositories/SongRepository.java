package nl.nickthijssen.persistence.repositories;

import nl.nickthijssen.persistence.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "songs", path = "songs")
public interface SongRepository extends JpaRepository<Song, Integer> {

    List<Song> findByTitle(@Param("title") String title);
}
