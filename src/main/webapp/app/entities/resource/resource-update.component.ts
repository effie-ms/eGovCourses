import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';

import { IResource } from 'app/shared/model/resource.model';
import { ResourceService } from './resource.service';
import { ILesson } from 'app/shared/model/lesson.model';
import { LessonService } from 'app/entities/lesson';

@Component({
    selector: 'egov-resource-update',
    templateUrl: './resource-update.component.html'
})
export class ResourceUpdateComponent implements OnInit {
    resource: IResource;
    isSaving: boolean;

    lessons: ILesson[];
    lessonId: number;

    constructor(
        private jhiAlertService: JhiAlertService,
        private resourceService: ResourceService,
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
        this.activatedRoute.data.subscribe(({ resource }) => {
            this.resource = resource;
        });
        this.lessonService.query().subscribe(
            (res: HttpResponse<ILesson[]>) => {
                this.lessons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.lessonId === 0 && this.resource.lessonId !== null) {
            this.lessonId = this.resource.lessonId;
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resource.id !== undefined) {
            this.resource.lessonId = this.lessonId;
            this.subscribeToSaveResponse(this.resourceService.update(this.resource));
        } else {
            this.resource.lessonId = this.lessonId;
            this.subscribeToSaveResponse(this.resourceService.create(this.resource));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResource>>) {
        result.subscribe((res: HttpResponse<IResource>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
