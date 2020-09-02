
package acme.features.administrator.inquiry;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiry.Inquiry;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInquiryRepository extends AbstractRepository {

	@Query("select t from Inquiry t")
	Collection<Inquiry> findAllInquiry();

	@Query("select n from Inquiry n where n.id = ?1")
	Inquiry findInquiryById(int id);

}
