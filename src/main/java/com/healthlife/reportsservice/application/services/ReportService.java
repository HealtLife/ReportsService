package com.healthlife.reportsservice.application.services;

import com.healthlife.reportsservice.application.dto.*;
import com.healthlife.reportsservice.application.mapper.ReportMapper;
import com.healthlife.reportsservice.domain.models.agregates.Reportes;
import com.healthlife.reportsservice.infrastructure.repository.ReporteRepository;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    private final ReporteRepository reportesRepository;
    private final RestTemplate restTemplate;
    private String filename = new String();
    private final ReportMapper reportMapper;

    public ReportService(ReporteRepository reportesRepository, ReportMapper reportMapper) {
        this.reportesRepository = reportesRepository;
        this.reportMapper = reportMapper;
        this.restTemplate = new RestTemplate();
    }

    public List<ReportDto> obtenerReportesPorUsuario(String userDni) {
        List<Reportes> reportes = reportesRepository.findByUserDni(userDni);
        return reportes.stream().map(reportMapper::toDto).toList();
    }

    public Reportes crearReporte(CreateReportRequestDto dto) {
        String dni = dto.getUserDni();
        System.out.println("DNI: " + dni);
        //Alergias
        List<AllergyRequestDto> alergias = List.of();
        if (dto.isAllergies()) {
            String url = "http://localhost:8086/api/v1/medical-history/allergies/" + dni; // ajusta puerto si es necesario
            AllergyRequestDto[] response = restTemplate.getForObject(url, AllergyRequestDto[].class);
            alergias = Arrays.asList(response);
        }
        //Medical-Notes
        List<MedicalNotesRequestDto> notes = List.of();
        if (dto.isNotes()) {
            String urlN = "http://localhost:8086/api/v1/medical-history/medical-note/" + dni;
            MedicalNotesRequestDto[] responseNotes = restTemplate.getForObject(urlN, MedicalNotesRequestDto[].class);
            notes = Arrays.asList(responseNotes);
        }
        //Personal-Info
        List<PersonalInfoRequestDto> info = List.of();
        if (dto.isInfo()) {
            String urlI = "http://localhost:8086/api/v1/medical-history/personal-info/" + dni;
            PersonalInfoRequestDto responseInfo = restTemplate.getForObject(urlI, PersonalInfoRequestDto.class);
            info = Arrays.asList(responseInfo);
        }
        //Prescription
        List<PrescriptionRequestDto> prescription = List.of();
        if (dto.isPrescription()) {
            String urlP = "http://localhost:8086/api/v1/medical-history/prescription/" + dni;
            PrescriptionRequestDto[] responsePrescription = restTemplate.getForObject(urlP, PrescriptionRequestDto[].class);
            prescription = Arrays.asList(responsePrescription);
        }

        //Vaccine
        List<VaccineRequestDto> vaccine = List.of();
        if (dto.isVaccine()) {
            String urlV = "http://localhost:8086/api/v1/medical-history/vaccine/" + dni;
            VaccineRequestDto[] responseVaccine = restTemplate.getForObject(urlV, VaccineRequestDto[].class);
            vaccine = Arrays.asList(responseVaccine);
        }

        //Weight and Height
        List<WeigthHegthDto> weightHeight = List.of();
        if (dto.isWeightHeight()) {
            String urlW = "http://localhost:8086/api/v1/medical-history/weightheight/" + dni;
            WeigthHegthDto[] responseW = restTemplate.getForObject(urlW, WeigthHegthDto[].class);
            weightHeight = Arrays.asList(responseW);
        }

        generarPdfReporte(dni,dto,alergias,notes,info,prescription,vaccine,weightHeight);

        Reportes reporte = new Reportes();
        reporte.setTitulo(dto.getTitulo());
        reporte.setFecha_creacion(java.time.LocalDate.now().toString());
        reporte.setEstado("json"); // o "pdf", si luego se exporta
        reporte.setUserDni(dni);

        reporte.setUrl(this.filename);

        return reportesRepository.save(reporte);
    }

    public void generarPdfReporte(String dni, CreateReportRequestDto dto,
                                  List<AllergyRequestDto> alergias,
                                  List<MedicalNotesRequestDto> notes,
                                  List<PersonalInfoRequestDto> info,
                                  List<PrescriptionRequestDto> prescriptions,
                                  List<VaccineRequestDto> vaccines,
                                  List<WeigthHegthDto> weightHeight) {

        try {
            Document document = new Document();
            this.filename = "reporte_" + dni + "_" + System.currentTimeMillis() + ".pdf";
            String outputPath = "src/main/resources/pdfs/" + filename;
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            // Título
            document.add(new Paragraph("Reporte Médico", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(new Paragraph("DNI: " + dni));
            document.add(new Paragraph("Título del Reporte: " + dto.getTitulo()));
            document.add(new Paragraph("Fecha: " + java.time.LocalDate.now()));
            document.add(Chunk.NEWLINE);

            // Alergias
            if (!alergias.isEmpty()) {
                document.add(new Paragraph("Alergias:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                for (AllergyRequestDto a : alergias) {
                    document.add(new Paragraph("- " + a.getAlergia() + " | Reacción: " + a.getReaccion()));
                }
                document.add(Chunk.NEWLINE);
            }

            // Notas médicas
            if (!notes.isEmpty()) {
                document.add(new Paragraph("Notas Médicas:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                for (MedicalNotesRequestDto n : notes) {
                    document.add(new Paragraph("- " + n.getAutor() + ": " + n.getNotes() ));
                }
                document.add(Chunk.NEWLINE);
            }

            // Información personal
            if (!info.isEmpty()) {
                document.add(new Paragraph("Información Personal:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                for (PersonalInfoRequestDto i : info) {
                    document.add(new Paragraph("- Dni: " + i.getDni() + ", Genero: " + i.getGenero()) );
                    document.add(new Paragraph("- Fecha Nacimiento: " + i.getFechaNacimiento() + ", Tipo Cuerpo: " + i.getTipoCuerpo()) );
                    document.add(new Paragraph("- IMC: " + i.getImc()));
                }
                document.add(Chunk.NEWLINE);
            }

            // Prescripciones
            if (!prescriptions.isEmpty()) {
                document.add(new Paragraph("Prescripciones:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                for (PrescriptionRequestDto p : prescriptions) {
                    document.add(new Paragraph("- Dni: " + p.getDni()));
                    document.add(new Paragraph("- Prescription: " + p.getPrescripcion()));
                    document.add(new Paragraph("- Fecha Receta: " +p.getFecha_receta() + ", Medico" + p.getMedico()));
                }
                document.add(Chunk.NEWLINE);
            }

            // Vacunas
            if (!vaccines.isEmpty()) {
                document.add(new Paragraph("Vacunas:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                for (VaccineRequestDto v : vaccines) {
                    document.add(new Paragraph("- DNI " + v.getDni() + " | Fecha: " + v.getFechaAplicacion()));
                    document.add(new Paragraph("- Vacuna " + v.getVacuna() + ", Dosis: " + v.getDosis()));
                }
                document.add(Chunk.NEWLINE);
            }

            // Peso y altura
            if (!weightHeight.isEmpty()) {
                document.add(new Paragraph("Peso y Altura:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                for (WeigthHegthDto wh : weightHeight) {
                    document.add(new Paragraph("- Peso: " + wh.getPeso() + "kg | Altura: " + wh.getAltura() + "cm"));
                }
                document.add(Chunk.NEWLINE);
            }

            document.close();
            System.out.println("PDF generado: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
