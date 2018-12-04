import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';

import { ITask } from 'app/shared/model/task.model';
import { TaskService } from './task.service';
import { ILesson } from 'app/shared/model/lesson.model';
import { LessonService } from 'app/entities/lesson';

@Component({
    selector: 'egov-task-update',
    templateUrl: './task-update.component.html'
})
export class TaskUpdateComponent implements OnInit {
    task: ITask;
    isSaving: boolean;

    lessons: ILesson[];
    lessonId: number;

    constructor(
        private jhiAlertService: JhiAlertService,
        private taskService: TaskService,
        private lessonService: LessonService,
        private activatedRoute: ActivatedRoute
    ) {
        this.activatedRoute.paramMap.pipe(
            switchMap((params: ParamMap) =>
                of(params.get('lessonId'))
            )
        ).subscribe(d => {
            this.lessonId = +d;
        });
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ task }) => {
            this.task = task;
        });
        this.lessonService.query().subscribe(
            (res: HttpResponse<ILesson[]>) => {
                this.lessons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.lessonId === 0 && this.task.lessonId !== null) {
            this.lessonId = this.task.lessonId;
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.task.id !== undefined) {
            this.task.lessonId = this.lessonId;
            this.subscribeToSaveResponse(this.taskService.update(this.task));
        } else {
            this.task.lessonId = this.lessonId;
            this.subscribeToSaveResponse(this.taskService.create(this.task));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITask>>) {
        result.subscribe((res: HttpResponse<ITask>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackLessonById(index: number, item: ILesson) {
        return item.id;
    }
}
