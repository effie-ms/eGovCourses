import { ILesson } from 'app/shared/model//lesson.model';

export const enum Level {
    NOVICE = 'NOVICE',
    BEGINNER = 'BEGINNER',
    INTERMEDIATE = 'INTERMEDIATE',
    ADVANCED = 'ADVANCED',
    PROFESSIONAL = 'PROFESSIONAL'
}

export interface ICourse {
    id?: number;
    courseTitle?: string;
    courseDescription?: string;
    coursePrice?: number;
    courseLevel?: Level;
    lessons?: ILesson[];
}

export class Course implements ICourse {
    constructor(
        public id?: number,
        public courseTitle?: string,
        public courseDescription?: string,
        public coursePrice?: number,
        public courseLevel?: Level,
        public lessons?: ILesson[]
    ) {}
}
