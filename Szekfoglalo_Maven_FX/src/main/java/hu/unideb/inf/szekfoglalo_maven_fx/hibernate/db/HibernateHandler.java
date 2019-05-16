/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.szekfoglalo_maven_fx.hibernate.db;

import hu.unideb.inf.szekfoglalo_maven_fx.model.Seat;
import hu.unideb.inf.szekfoglalo_maven_fx.model.Show;
import hu.unideb.inf.szekfoglalo_maven_fx.model.User;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Adamoc
 */
public class HibernateHandler {
    static Session session;//= HibernateUtil.getSessionFactory().openSession();
    
    public static void UploadUser(User tmp) {
        session = HibernateUtil.getSessionFactory().openSession();      
        session.beginTransaction();
        session.save(tmp);
        session.getTransaction().commit();
        session.close();
    }
    
    public static List<User> DownloadUsers() { 
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> list = session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
    
    public static void UploadBooking(User user, Show show, Seat seat) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user1 = (User)session.get(User.class, user.getName());
        user1.addBooking(show, seat);
        session.getTransaction().commit();
        session.close();
    }
    
    public static void DeleteBooking(User user, Show show, Seat seat) {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User user1 = (User)session.get(User.class, user.getName());
            user1.deleteBooking(show, seat);
            session.getTransaction().commit();
            session.close();       
    }
}
