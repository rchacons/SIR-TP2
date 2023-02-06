package dao;

import domain.Person;
import domain.SupportMember;
import domain.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PersonDAO extends GenericDaoJpaImpl<Person,Long> {

    public List<Person> getAllUsers(){
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.type(), User.class));

        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    public int getNumberOfUsers(){
        return getAllUsers().size();
    }


    public List<Person> getAllSupportMembers(){
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.type(), SupportMember.class));

        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    public int getNumberOfSupportMembers(){
        return getAllSupportMembers().size();
    }
}
