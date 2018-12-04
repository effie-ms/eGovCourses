export interface ITask {
    id?: number;
    taskTitle?: string;
    taskDescription?: string;
    lessonId?: number;
}

export class Task implements ITask {
    constructor(public id?: number, public taskTitle?: string, public taskDescription?: string, public lessonId?: number) {}
}
