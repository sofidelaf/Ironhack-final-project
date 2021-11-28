export class Category {
    
    constructor(
        private _type: string,
        private _description: string,
        private _creationDate: Date,
        private _userCreation: string,
        private _modificationDate: Date,
        private _userModification: string
    ) {}

    public get userModification(): string {
        return this._userModification;
    }
    public set userModification(value: string) {
        this._userModification = value;
    }
    public get modificationDate(): Date {
        return this._modificationDate;
    }
    public set modificationDate(value: Date) {
        this._modificationDate = value;
    }
    public get userCreation(): string {
        return this._userCreation;
    }
    public set userCreation(value: string) {
        this._userCreation = value;
    }
    public get creationDate(): Date {
        return this._creationDate;
    }
    public set creationDate(value: Date) {
        this._creationDate = value;
    }
    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get type(): string {
        return this._type;
    }
    public set type(value: string) {
        this._type = value;
    }
}