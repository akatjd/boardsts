package com.board.aop;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TansactionAspect {
	
	private static final String AOP_TRANSACTION_METHOD_NAME = "*";
	private static final String AOP_TRANSACTION_EXPRESSION = "execution(* com.board..service.*Impl.*(..))";
	
	@Autowired
	private TransactionManager transactionManager;
	
//	private static final String EXPRESSION = "execution(* com.board..service.*Impl.*(..))";
	
	@Bean
	public TransactionInterceptor transactionAdvice() {
		
//		List<RollbackRuleAttribute> rollbackRules = Collections.singletonList(new RollbackRuleAttribute(Exception.class));
//		
//		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
//		transactionAttribute.setRollbackRules(rollbackRules);
//		transactionAttribute.setName("*");
//		
//		MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
//		
//		attributeSource.setTransactionAttribute(transactionAttribute);
		
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME);
		transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		source.setTransactionAttribute(transactionAttribute);
		
		return new TransactionInterceptor(transactionManager, source);
	}
	
	@Bean
	public Advisor transactionAdvisor() {
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_TRANSACTION_EXPRESSION);
		
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
}
