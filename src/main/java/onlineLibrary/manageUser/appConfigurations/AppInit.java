package onlineLibrary.manageUser.appConfigurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }
    @Override   // note 1
    protected Class<?>[]getServletConfigClasses(){
        return null;
    }
}
