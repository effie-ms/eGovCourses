import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILesson } from 'app/shared/model/lesson.model';

@Component({
    selector: 'egov-lesson-detail',
    templateUrl: './lesson-detail.component.html'
})
export class LessonDetailComponent implements OnInit {
    lesson: ILesson;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ lesson }) => {
            this.lesson = lesson;
        });
    }

    previousState() {
        window.history.back();
    }
}
