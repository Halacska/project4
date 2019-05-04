/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.szekfoglalo_maven_fx.hibernate.db;

import hu.unideb.inf.szekfoglalo_maven_fx.model.User;
import org.hibernate.Session;

/**
 *
 * @author Adamoc
 */
public class HibernateHandler {
    static Session session;//= HibernateUtil.getSessionFactory().openSession();
    
    public static void UplodeUser(User tmp) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(tmp);
        session.getTransaction().commit();
        session.close();
    }
}
