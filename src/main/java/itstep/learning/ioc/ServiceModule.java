package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.hash.Md5HasService;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(HashService.class).to(Md5HasService.class);
    }
}
