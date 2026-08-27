package livecoding.oop;

import java.util.List;

// Абстрактный: название общее, но отчёт без конкретного формата сгенерировать нельзя
abstract class Report {
    private final String title;

    public Report(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Абстрактный: каждый формат имеет собственный способ генерации
    public abstract void generate();
}

class PdfReport extends Report {
    public PdfReport(String title) {
        super(title);
    }

    @Override
    public void generate() {
        System.out.println("PDF отчет \"" + getTitle() + "\" сгенерирован");
    }
}

class ExcelReport extends Report {
    public ExcelReport(String title) {
        super(title);
    }

    @Override
    public void generate() {
        System.out.println("Excel отчет \"" + getTitle() + "\" сгенерирован");
    }
}

class HtmlReport extends Report {
    public HtmlReport(String title) {
        super(title);
    }

    @Override
    public void generate() {
        System.out.println("HTML отчет \"" + getTitle() + "\" сгенерирован");
    }
}

class ReportService {
    public void generateAll(List<Report> reports) {
        for (Report report : reports) {
            report.generate(); // Формат отчёта выбирается полиморфно
        }
    }
}

public class Task07Demo {
    public static void main(String[] args) {
        List<Report> reports = List.of(
                new PdfReport("Продажи за март"),
                new ExcelReport("Зарплаты"),
                new HtmlReport("Статистика сайта")
        );

        ReportService reportService = new ReportService();
        reportService.generateAll(reports);
    }
}