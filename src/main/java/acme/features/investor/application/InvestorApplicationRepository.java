
package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.investmentround.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select t from Application t where t.id = ?1")
	Application findApplicationById(int id);

	@Query("select t from Application t where t.investor.id = ?1")
	Collection<Application> findApplicationByInvestorId(int id);

	@Query("select t from InvestmentRound t where t.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select t from Investor t where t.userAccount.id = ?1")
	Investor findInvestorByUserAccountId(int investorId);

}
