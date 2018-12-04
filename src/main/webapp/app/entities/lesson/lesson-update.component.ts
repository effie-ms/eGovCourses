import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';

import { ILesson } from 'app/shared/model/lesson.model';
import { LessonService } from './lesson.service';
import { ICourse } from 'app/shared/model/course.model';
import { CourseService } from 'app/entities/course';

@Component({
    selector: 'egov-lesson-update',
    templateUrl: './lesson-update.component.html'
})
export class LessonUpdateComponent implements OnInit {
    lesson: ILesson;
    isSaving: boolean;

    courses: ICourse[];
    courseId: number;

    constructor(
        private jhiAlertService: JhiAlertService,
        private lessonService: LessonService,
        private courseService: CourseService,
        private activatedRoute: ActivatedRoute
    ) {
        this.activatedRoute.paramMap.pipe(
            switchMap((params: ParamMap) =>
                of(params.get('courseId'))
            )
        ).subscribe(d => {
            this.courseId = +d;
        });
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ lesson }) => {
            this.lesson = lesson;
        });
        this.courseService.query().subscribe(
            (res: HttpResponse<ICourse[]>) => {
                this.courses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.courseId === 0 && this.lesson.courseId !== null) {
            this.courseId = this.lesson.courseId;
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.lesson.id !== undefined) {
            this.lesson.courseId = this.courseId;
            this.subscribeToSaveResponse(this.lessonService.update(this.lesson));
        } else {
            this.lesson.courseId = this.courseId;
            this.subscribeToSaveResponse(this.lessonService.create(this.lesson));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILesson>>) {
        result.subscribe((res: HttpResponse<ILesson>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCourseById(index: number, item: ICourse) {
        return item.id;
    }
}
