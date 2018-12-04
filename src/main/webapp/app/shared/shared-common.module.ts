import { NgModule } from '@angular/core';

import { EgovcoursesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [EgovcoursesSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [EgovcoursesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class EgovcoursesSharedCommonModule {}
