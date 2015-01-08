package br.com.onlance.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import br.com.onlance.utils.HibernateUtils;

//@WebFilter(urlPatterns="/*")
public class OpenSessionAndTransactionInView implements Filter {

	SessionFactory sf;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {

			sf.getCurrentSession().beginTransaction();
			chain.doFilter(request, response);
			sf.getCurrentSession().getTransaction().commit();
			sf.getCurrentSession().close();
		} catch (Throwable t) {
			try {
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable ex) {
				ex.printStackTrace();
			}

			throw new ServletException(t);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		sf = HibernateUtils.getSessionFactory();
	}

}
