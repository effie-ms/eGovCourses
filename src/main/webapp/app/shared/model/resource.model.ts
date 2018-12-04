export const enum ResourceType {
    VIDEO = 'VIDEO',
    IMAGE = 'IMAGE',
    TUTORIAL = 'TUTORIAL',
    PAGE = 'PAGE',
    PARTIAL = 'PARTIAL',
    TOOL = 'TOOL'
}

export interface IResource {
    id?: number;
    resourceName?: string;
    resourceDescription?: string;
    resourceURL?: string;
    resourceType?: ResourceType;
    lessonId?: number;
}

export class Resource implements IResource {
    constructor(
        public id?: number,
        public resourceName?: string,
        public resourceDescription?: string,
        public resourceURL?: string,
        public resourceType?: ResourceType,
        public lessonId?: number
    ) {}
}
