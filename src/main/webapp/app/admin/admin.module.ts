import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { EgovcoursesSharedModule } from 'app/shared';

import {
    adminState,
    UserMgmtComponent,
    UserMgmtDetailComponent,
    UserMgmtUpdateComponent,
    UserMgmtDeleteDialogComponent
} from './';

@NgModule({
    imports: [
        EgovcoursesSharedModule,
        RouterModule.forChild(adminState)
    ],
    declarations: [
        UserMgmtComponent,
        UserMgmtDetailComponent,
        UserMgmtUpdateComponent,
        UserMgmtDeleteDialogComponent
    ],
    entryComponents: [UserMgmtDeleteDialogComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EgovcoursesAdminModule {}
