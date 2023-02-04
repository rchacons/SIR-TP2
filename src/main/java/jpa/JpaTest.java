package jpa;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Queue;

public class JpaTest {

	private EntityManager manager;
	public JpaTest(EntityManager manager){
		this.manager=manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager manager = EntityManagerHelper.getEntityManager();
		JpaTest jpaTest = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();

		tx.begin();


		try {

			jpaTest.createUsers();
			jpaTest.createSupportMembers();
			jpaTest.createTickets();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		jpaTest.listUsers();

		// implementing criteria queries
		jpaTest.listBugTickets();
		jpaTest.listFeatureTickets();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	}



	private void createSupportMembers() {
		int numOfSupportMembers = manager.createQuery("Select s From SupportMember s", SupportMember.class).getResultList().size();

		SupportMember sp1 = new SupportMember();
		sp1.setName("SupportMember1");

		SupportMember sp2 = new SupportMember();
		sp2.setName("SupportMember2");

		SupportMember sp3 = new SupportMember();
		sp3.setName("SupportMember3");

		if(numOfSupportMembers == 0){
			manager.persist(sp1);
			manager.persist(sp2);
			manager.persist(sp3);
		}
	}

	private void createUsers() {
		int numOfUsers = manager.createQuery("Select u From User u", User.class).getResultList().size();

		if(numOfUsers == 0){
			manager.persist(new User("User1"));
			manager.persist(new User("User2"));
		}
	}

	private void createTickets() {

		List<User> resultList = manager.createQuery("select u from User u", User.class).getResultList();
		List<SupportMember> resultList2 = manager.createQuery("select s from SupportMember s",SupportMember.class).getResultList();

		if(resultList.size()>=1){
			User user = resultList.get(0);
			SupportMember sp1 = resultList2.get(0);
			SupportMember sp2 = resultList2.get(1);

			Ticket ticket = new BugForm();
			ticket.setUser(user);
			ticket.setSupportMemberList(List.of(sp1,sp2));
			ticket.setTitle("Bug Ticket test");
			ticket.setState(StateEnum.OPEN);
			ticket.setTag(TagEnum.TAG1);
			manager.persist(ticket);

			User user1 = resultList.get(1);
			SupportMember sp3 = resultList2.get(2);

			Ticket ticket1 = new FeatureRequestForm();
			//ticket1.setSupportMemberList(List.of(sp3)); we're not going to assign a support yet to a feature form
			ticket1.setUser(user1);
			ticket1.setTitle("Request form test");
			ticket1.setState(StateEnum.OPEN);
			ticket1.setTag(TagEnum.TAG2);
			manager.persist(ticket1);
		}


	}

	private void listUsers() {
		List<User> resultList = manager.createQuery("select u from User u", User.class).getResultList();
		for(User nextUser : resultList){
			System.out.println("user:"+nextUser.getName());
		}
	}

	private void listBugTickets() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
		Root<Ticket> root = criteriaQuery.from(Ticket.class);
		criteriaQuery.select(root);
		//criteriaQuery.where(criteriaBuilder.equal(root.get("type"),"B"));
		criteriaQuery.where(criteriaBuilder.equal(root.type(),BugForm.class));

		List<Ticket> tickets = manager.createQuery(criteriaQuery).getResultList();
		for(Ticket ticket : tickets){
			System.out.println("Ticket:"+ticket.getTitle());
		}

	}

	private void listFeatureTickets() {

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
		Root<Ticket> root = criteriaQuery.from(Ticket.class);
		criteriaQuery.select(root);
		//criteriaQuery.where(criteriaBuilder.equal(root.get("type"),"FR"));
		criteriaQuery.where(criteriaBuilder.equal(root.type(),FeatureRequestForm.class));

		List<Ticket> tickets = manager.createQuery(criteriaQuery).getResultList();
		for(Ticket ticket : tickets){
			System.out.println("Ticket:"+ticket.getTitle());
		}

	}




}
