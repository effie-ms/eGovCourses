import { IResource } from 'app/shared/model//resource.model';
import { ITask } from 'app/shared/model//task.model';

export const enum Language {
    ENGLISH = 'ENGLISH',
    ESTONIAN = 'ESTONIAN',
    FINNISH = 'FINNISH',
    RUSSIAN = 'RUSSIAN',
    GERMAN = 'GERMAN',
    SPANISH = 'SPANISH'
}

export interface ILesson {
    id?: number;
    lessonTitle?: string;
    lessonDescription?: string;
    language?: Language;
    courseId?: number;
    resources?: IResource[];
    tasks?: ITask[];
}

export class Lesson implements ILesson {
    constructor(
        public id?: number,
        public lessonTitle?: string,
        public lessonDescription?: string,
        public language?: Language,
        public courseId?: number,
        public resources?: IResource[],
        public tasks?: ITask[]
    ) {}
}
