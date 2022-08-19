import Receipt from "@/pojo/Receipt";

export default interface Order{
    seatNumber: string;
    time: string;
    remarks: string;
    payment: string;
    uid: string;
    receipts: Receipt[];
}