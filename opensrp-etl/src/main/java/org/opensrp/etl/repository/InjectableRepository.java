package org.opensrp.etl.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.opensrp.etl.entity.InjectableEntity;
import org.opensrp.etl.interfaces.RegisterRepository;
import org.opensrp.etl.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;

public class InjectableRepository implements
        RegisterRepository<InjectableEntity> {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ExceptionService exceptionService;

    public InjectableRepository() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void save(InjectableEntity injectableEntity) {
        try {
            getSession().save(injectableEntity);
        } catch (Exception e) {
            exceptionService.generatedEntityAndSave(injectableEntity
                    .getRelationalid(), e.fillInStackTrace().toString(),
                    "InjectableRepository", injectableEntity.getINSTANCEID(),
                    injectableEntity.get_id());
        }
    }

    @Override
    public boolean delete(InjectableEntity injectableEntity) {
        Query query = getSession().createQuery(
                "delete InjectableEntity where id = :ID");
        query.setParameter("ID", injectableEntity.getId());
        int result = query.executeUpdate();
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(InjectableEntity t) {
        // TODO Auto-generated method stub

    }

    @Override
    public InjectableEntity findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InjectableEntity findByCaseId(String caseID) {
        // TODO Auto-generated method stub
        return null;
    }

    public InjectableEntity findByCaseIdAndToday(String relationalid, Date today) {
        Criteria listInjectableCr = getSession().createCriteria(
                InjectableEntity.class);
        listInjectableCr.add(Restrictions.eq("relationalid", relationalid));
        listInjectableCr.add(Restrictions.eq("Today", today));
        List<InjectableEntity> listInjectable = listInjectableCr.list();
        return listInjectable.size() > 0 ? (InjectableEntity) listInjectable
                .get(0) : null;
    }

    private Session getSession() {
        Session session = this.sessionFactory.getCurrentSession();
        return session;
    }

}