export class Reservation{
    idReservation! : number;
    idUser! : number;
    idDoc! : number;
    isActive: boolean = true;
    date_reservation : Date= new Date();
}