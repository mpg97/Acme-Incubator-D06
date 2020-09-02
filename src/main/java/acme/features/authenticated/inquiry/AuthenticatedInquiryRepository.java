
package acme.features.authenticated.inquiry;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiry.Inquiry;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInquiryRepository extends AbstractRepository {

	@Query("select t from Inquiry t")
	Collection<Inquiry> findAllInquiry();

	@Query("select t from Inquiry t where t.id = ?1")
	Inquiry findInquiryById(int id);

	@Query("SELECT i FROM Inquiry i WHERE i.deadline > ?1")
	Collection<Inquiry> findAllActivesInquiries(Date d);

}
