export class Tache {
  private _id : number;
  private _nom : string;
  private _date : Date

  constructor(id:number,nom:string,date : Date) {
    this._id=id;
    this._nom=nom;
    this._date=date;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get nom(): string {
    return this._nom;

  }

  set nom(value: string) {
    this._nom = value;
  }

  get date(): Date {

    return this._date;
  }

  set date(value: Date) {
    this._date = value;

  }


}
