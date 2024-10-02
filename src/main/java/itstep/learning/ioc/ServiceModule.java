package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import itstep.learning.services.db.DbService;
import itstep.learning.services.db.MySqlDbService;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.hash.Md5HasService;
import itstep.learning.services.kdf.KdfService;
import itstep.learning.services.kdf.PbKdF1Service;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(HashService.class).to(Md5HasService.class);
        bind(KdfService.class).to(PbKdF1Service.class);
        bind(DbService.class).to(MySqlDbService.class);
    }
}
