package br.com.utilities.interfaces;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author gustavo
 *
 * @param <E>
 * @param <I> must be Number and Serializable
 */
public interface ICustomRepository<E, I extends Serializable>
		extends JpaRepository<E, I>, JpaSpecificationExecutor<E> { //CrudRepository<E, I>

}
