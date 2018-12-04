import './vendor.ts';

import { NgModule, Injector } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { Ng2Webstorage, LocalStorageService, SessionStorageService } from 'ngx-webstorage';
import { JhiEventManager } from 'ng-jhipster';

import { AuthInterceptor } from './blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { EgovcoursesSharedModule } from 'app/shared';
import { EgovcoursesCoreModule } from 'app/core';
import { EgovcoursesAppRoutingModule } from './app-routing.module';
import { EgovcoursesHomeModule } from './home/home.module';
import { EgovcoursesAccountModule } from './account/account.module';
import { EgovcoursesEntityModule } from './entities/entity.module';
import * as moment from 'moment';

import { JhiMainComponent, NavbarComponent, FooterComponent, ErrorComponent } from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        EgovcoursesAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'egov', separator: '-' }),
        EgovcoursesSharedModule,
        EgovcoursesCoreModule,
        EgovcoursesHomeModule,
        EgovcoursesAccountModule,
        EgovcoursesEntityModule
    ],
    declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, FooterComponent],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
            deps: [LocalStorageService, SessionStorageService]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [Injector]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [JhiEventManager]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [Injector]
        }
    ],
    bootstrap: [JhiMainComponent]
})
export class EgovcoursesAppModule {
    constructor(private dpConfig: NgbDatepickerConfig) {
        this.dpConfig.minDate = { year: moment().year() - 100, month: 1, day: 1 };
    }
}
