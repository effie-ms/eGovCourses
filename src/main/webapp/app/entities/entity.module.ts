import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { EgovcoursesCourseModule } from './course/course.module';
import { EgovcoursesLessonModule } from './lesson/lesson.module';
import { EgovcoursesResourceModule } from './resource/resource.module';
import { EgovcoursesTaskModule } from './task/task.module';

@NgModule({
    imports: [
        EgovcoursesCourseModule,
        EgovcoursesLessonModule,
        EgovcoursesResourceModule,
        EgovcoursesTaskModule,
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EgovcoursesEntityModule {}
