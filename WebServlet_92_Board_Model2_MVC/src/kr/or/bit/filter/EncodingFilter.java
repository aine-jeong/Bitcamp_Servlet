package kr.or.bit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		description = "어노테이션활용해서 필터 적용하기", 
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "UTF-8", description = "모든페이지한글처리")
		})
public class EncodingFilter implements Filter {

	private String encoding;
	
    public EncodingFilter() {
        
    }
    
	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding = fConfig.getInitParameter("encoding");
		System.out.println("Filter init: " + this.encoding);
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//요청
		if(request.getCharacterEncoding() == null) {
			//모든 페이지에 적용되는 공통사항
			request.setCharacterEncoding(this.encoding);
			System.out.println("encoding: " + this.encoding);
		}
		
		
		//요청과 응답 기준 Line
		chain.doFilter(request, response);
		//응답
	}


}
