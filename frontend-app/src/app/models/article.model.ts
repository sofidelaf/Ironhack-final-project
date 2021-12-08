import { Stock } from "./stock.model";

export class Article {
        
    constructor(
        private _id: number,
        private _name: string,
        private _category: string,
        private _brand: string,
        private _description: string,
        private _imageUrl: string,
        private _price: number,
        private _stockList: Stock[],
        private _localdate: Date,
        private _usercreation: string,
        private _modificationDate: Date,
        private _usermodification: string
    ){

    }

    public get usermodification(): string {
        return this._usermodification;
    }
    public set usermodification(value: string) {
        this._usermodification = value;
    }
    public get modificationDate(): Date {
        return this._modificationDate;
    }
    public set modificationDate(value: Date) {
        this._modificationDate = value;
    }
    public get usercreation(): string {
        return this._usercreation;
    }
    public set usercreation(value: string) {
        this._usercreation = value;
    }
    public get localdate(): Date {
        return this._localdate;
    }
    public set localdate(value: Date) {
        this._localdate = value;
    }
    public get stockList(): Stock[] {
        return this._stockList;
    }
    public set stockList(value: Stock[]) {
        this._stockList = value;
    }
    public get price(): number {
        return this._price;
    }
    public set price(value: number) {
        this._price = value;
    }
    public get imageUrl(): string {
        return this._imageUrl;
    }
    public set imageUrl(value: string) {
        this._imageUrl = value;
    }
    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get brand(): string {
        return this._brand;
    }
    public set brand(value: string) {
        this._brand = value;
    }
    public get category(): string {
        return this._category;
    }
    public set category(value: string) {
        this._category = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }    

}