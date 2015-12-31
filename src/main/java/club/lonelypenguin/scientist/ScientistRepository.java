/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.lonelypenguin.scientist;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dbundgaard
 */
public interface ScientistRepository extends JpaRepository<Scientist, Long> {
    
    public List<Scientist> findByName(String name);
    public List<Scientist> findByCountry(String country);
    
}
